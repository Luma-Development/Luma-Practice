package best.luma.practice.utils.task;

import best.luma.practice.Luma;
import org.bukkit.Bukkit;

public class TaskUtils {

    public static void run(Runnable runnable) {
        Luma.getInstance().getServer().getScheduler().runTask(Luma.getInstance(), runnable);
    }

    public static void runTaskLater(Runnable runnable, long delay) {
        Bukkit.getScheduler().runTaskLater(Luma.getInstance(), runnable, delay);
    }

    public static void runTaskTimer(Runnable runnable, long delay, long period) {
        Bukkit.getScheduler().runTaskTimer(Luma.getInstance(), runnable, delay, period);
    }

    public static void runLater(Runnable runnable, long delay) {
        Luma.getInstance().getServer().getScheduler().runTaskLater(Luma.getInstance(), runnable, delay);
    }

    public static void runTimer(Runnable runnable, long delay, long period) {
        Luma.getInstance().getServer().getScheduler().runTaskTimer(Luma.getInstance(), runnable, delay, period);
    }

    public static void runAsync(Runnable runnable) {
        try {
            Luma.getInstance().getServer().getScheduler().runTaskAsynchronously(Luma.getInstance(), runnable);
        } catch (IllegalStateException e) {
            Luma.getInstance().getServer().getScheduler().runTask(Luma.getInstance(), runnable);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
