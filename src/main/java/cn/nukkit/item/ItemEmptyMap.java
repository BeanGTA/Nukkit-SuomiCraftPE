package cn.nukkit.item;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.NukkitRunnable;

public class ItemEmptyMap extends Item {

    public ItemEmptyMap() {
        this(0, 1);
    }

    public ItemEmptyMap(Integer meta) {
        this(meta, 1);
    }

    public ItemEmptyMap(Integer meta, int count) {
        super(EMPTY_MAP, 0, count, "Empty Map");
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(Level level, Player player, Block block, Block target, BlockFace face, double fx, double fy, double fz) {
        if (!player.isCreative()) {
            new NukkitRunnable() {
                public void run() {
                    player.getInventory().removeItem(new ItemEmptyMap());
                    player.getInventory().addItem(new ItemMap());
                }
            }.runTaskLater(null, 1);
        } else player.getInventory().addItem(new ItemMap());
        return true;
    }

    @Override
    public boolean onClickAir(Player player, Vector3 directionVector) {
        if (!player.isCreative()) {
            new NukkitRunnable() {
                public void run() {
                    player.getInventory().removeItem(new ItemEmptyMap());
                    player.getInventory().addItem(new ItemMap());
                }
            }.runTaskLater(null, 1);
        } else player.getInventory().addItem(new ItemMap());
        return true;
    }
}
