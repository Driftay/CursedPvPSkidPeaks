package org.saberdev.demoneye.util;

/**
 * @Author: Driftay
 * @Date: 2/13/2022 12:48 AM
 */
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

/**
 * @Author: Driftay
 * @Date: 2/11/2022 8:41 AM
 */
public class CountdownTimer implements Runnable {

    private final JavaPlugin plugin;

    private Integer assignedTaskId;

    private final int seconds;
    private int secondsLeft;

    private final Consumer<CountdownTimer> everySecond;

    private final Runnable beforeTimer;

    private final Runnable afterTimer;

    public CountdownTimer(JavaPlugin plugin, int seconds, Runnable beforeTimer, Consumer<CountdownTimer> everySecond, Runnable afterTimer) {
        this.plugin = plugin;
        this.seconds = seconds;
        this.secondsLeft = seconds;
        this.beforeTimer = beforeTimer;
        this.afterTimer = afterTimer;
        this.everySecond = everySecond;
    }

    public CountdownTimer(JavaPlugin plugin, int seconds, Consumer<CountdownTimer> everySecond, Runnable afterTimer) {
        this.plugin = plugin;
        this.seconds = seconds;
        this.secondsLeft = seconds;
        this.beforeTimer = null;
        this.afterTimer = afterTimer;
        this.everySecond = everySecond;
    }

    public CountdownTimer(JavaPlugin plugin, int seconds, Runnable beforeTimer, Consumer<CountdownTimer> everySecond) {
        this.plugin = plugin;
        this.seconds = seconds;
        this.secondsLeft = seconds;
        this.beforeTimer = beforeTimer;
        this.afterTimer = null;
        this.everySecond = everySecond;
    }

    public CountdownTimer(JavaPlugin plugin, int seconds, Consumer<CountdownTimer> everySecond) {
        this.plugin = plugin;
        this.seconds = seconds;
        this.secondsLeft = seconds;
        this.beforeTimer = null;
        this.afterTimer = null;
        this.everySecond = everySecond;
    }

    @Override
    public void run() {
        if (secondsLeft < 1) {
            if (afterTimer != null) afterTimer.run();
            if (assignedTaskId != null) Bukkit.getScheduler().cancelTask(assignedTaskId);
            return;
        }

        if (secondsLeft == seconds && beforeTimer != null) beforeTimer.run();

        everySecond.accept(this);

        secondsLeft--;
    }

    public int getTotalSeconds() {
        return seconds;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    public Integer getAssignedTaskId() {
        return assignedTaskId;
    }

    public void cancelCountdown() {
        Bukkit.getScheduler().cancelTask(assignedTaskId);
    }

    public void scheduleTimer() {
        this.assignedTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, 20L);
    }

    public boolean isImportantSecond() {
        return ((this.secondsLeft % 15) == 0) || (this.secondsLeft < 11 && this.secondsLeft >= 1);
    }
}
