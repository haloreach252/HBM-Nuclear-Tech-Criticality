package com.miniverse.hbm.api.energymk2;

import java.util.*;

import com.miniverse.hbm.api.energymk2.IEnergyProviderMK2;
import com.miniverse.hbm.api.energymk2.IEnergyReceiverMK2;
import com.miniverse.hbm.uninos.NodeNet;
import com.miniverse.hbm.util.Tuple.Pair;
import com.miniverse.hbm.energymk2.Nodespace.PowerNode;
import com.miniverse.hbm.api.energymk2.IEnergyReceiverMK2.ConnectionPriority;

/**
 * Technically MK3 since it's now UNINOS compatible, although UNINOS was built out of 95% Nodespace code.
 *
 * @author hbm
 */
public class PowerNetMK2 extends NodeNet<IEnergyReceiverMK2, IEnergyProviderMK2, PowerNode> {

    public long energyTracker = 0L;
    protected static final int timeout = 3_000;
    private static final Random rand = new Random();

    @Override
    public void resetTrackers() {
        this.energyTracker = 0;
    }

    @Override
    public void update() {

        if (providerEntries.isEmpty() || receiverEntries.isEmpty()) return;

        long timestamp = System.currentTimeMillis();

        List<Pair<IEnergyProviderMK2, Long>> providers = new ArrayList<>();
        long powerAvailable = 0;

        Iterator<Entry<IEnergyProviderMK2, Long>> provIt = providerEntries.entrySet().iterator();
        while (provIt.hasNext()) {
            Entry<IEnergyProviderMK2, Long> entry = provIt.next();
            if (timestamp - entry.getValue() > timeout) {
                provIt.remove();
                continue;
            }
            long src = Math.min(entry.getKey().getPower(), entry.getKey().getProviderSpeed());
            providers.add(new Pair<>(entry.getKey(), src));
            powerAvailable += src;
        }

        @SuppressWarnings("unchecked")
        List<Pair<IEnergyReceiverMK2, Long>>[] receivers = new ArrayList[ConnectionPriority.values().length];
        for (int i = 0; i < receivers.length; i++) receivers[i] = new ArrayList<>();
        long[] demand = new long[ConnectionPriority.values().length];
        long totalDemand = 0;

        Iterator<Entry<IEnergyReceiverMK2, Long>> recIt = receiverEntries.entrySet().iterator();
        while (recIt.hasNext()) {
            Entry<IEnergyReceiverMK2, Long> entry = recIt.next();
            if (timestamp - entry.getValue() > timeout) {
                recIt.remove();
                continue;
            }
            long rec = Math.min(entry.getKey().getMaxPower() - entry.getKey().getPower(), entry.getKey().getReceiverSpeed());
            int p = entry.getKey().getPriority().ordinal();
            receivers[p].add(new Pair<>(entry.getKey(), rec));
            demand[p] += rec;
            totalDemand += rec;
        }

        long toTransfer = Math.min(powerAvailable, totalDemand);
        long energyUsed = 0;

        for (int i = ConnectionPriority.values().length - 1; i >= 0; i--) {
            List<Pair<IEnergyReceiverMK2, Long>> list = receivers[i];
            long priorityDemand = demand[i];

            for (Pair<IEnergyReceiverMK2, Long> entry : list) {
                double weight = (double) entry.getValue() / (double) (priorityDemand);
                long toSend = (long) Math.max(toTransfer * weight, 0D);
                energyUsed += (toSend - entry.getKey().transferPower(toSend));
            }

            toTransfer -= energyUsed;
        }

        this.energyTracker += energyUsed;
        long leftover = energyUsed;

        for (Pair<IEnergyProviderMK2, Long> entry : providers) {
            double weight = (double) entry.getValue() / (double) powerAvailable;
            long toUse = (long) Math.max(energyUsed * weight, 0D);
            entry.getKey().usePower(toUse);
            leftover -= toUse;
        }

        // Rounding error compensation, prevents unaccounted energy losses
        int iterationsLeft = 100;
        while (iterationsLeft > 0 && leftover > 0 && !providers.isEmpty()) {
            iterationsLeft--;

            Pair<IEnergyProviderMK2, Long> selected = providers.get(rand.nextInt(providers.size()));
            IEnergyProviderMK2 scapegoat = selected.getKey();

            long toUse = Math.min(leftover, scapegoat.getPower());
            scapegoat.usePower(toUse);
            leftover -= toUse;
        }
    }

    /**
     * Sends power using a diode-like method.
     *
     * @param power The amount of power to be sent.
     * @return The remaining power after distribution.
     */
    public long sendPowerDiode(long power) {

        if (receiverEntries.isEmpty()) return power;

        long timestamp = System.currentTimeMillis();

        @SuppressWarnings("unchecked")
        List<Pair<IEnergyReceiverMK2, Long>>[] receivers = new ArrayList[ConnectionPriority.values().length];
        for (int i = 0; i < receivers.length; i++) receivers[i] = new ArrayList<>();
        long[] demand = new long[ConnectionPriority.values().length];
        long totalDemand = 0;

        Iterator<Entry<IEnergyReceiverMK2, Long>> recIt = receiverEntries.entrySet().iterator();
        while (recIt.hasNext()) {
            Entry<IEnergyReceiverMK2, Long> entry = recIt.next();
            if (timestamp - entry.getValue() > timeout) {
                recIt.remove();
                continue;
            }
            long rec = Math.min(entry.getKey().getMaxPower() - entry.getKey().getPower(), entry.getKey().getReceiverSpeed());
            int p = entry.getKey().getPriority().ordinal();
            receivers[p].add(new Pair<>(entry.getKey(), rec));
            demand[p] += rec;
            totalDemand += rec;
        }

        long toTransfer = Math.min(power, totalDemand);
        long energyUsed = 0;

        for (int i = ConnectionPriority.values().length - 1; i >= 0; i--) {
            List<Pair<IEnergyReceiverMK2, Long>> list = receivers[i];
            long priorityDemand = demand[i];

            for (Pair<IEnergyReceiverMK2, Long> entry : list) {
                double weight = (double) entry.getValue() / (double) (priorityDemand);
                long toSend = (long) Math.max(toTransfer * weight, 0D);
                energyUsed += (toSend - entry.getKey().transferPower(toSend));
            }

            toTransfer -= energyUsed;
        }

        this.energyTracker += energyUsed;

        return power - energyUsed;
    }

    public static final ReceiverComparator COMP = new ReceiverComparator();

    /**
     * Comparator for sorting energy receivers based on priority.
     */
    public static class ReceiverComparator implements Comparator<IEnergyReceiverMK2> {
        @Override
        public int compare(IEnergyReceiverMK2 o1, IEnergyReceiverMK2 o2) {
            return o2.getPriority().ordinal() - o1.getPriority().ordinal();
        }
    }
}
