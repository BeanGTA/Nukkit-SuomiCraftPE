package cn.nukkit.utils.spawners;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.passive.EntityCow;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.level.generator.biome.Biome;
import cn.nukkit.utils.AbstractEntitySpawner;
import cn.nukkit.utils.Spawner;
import cn.nukkit.utils.SpawnResult;

public class CowSpawner extends AbstractEntitySpawner {

    public CowSpawner(Spawner spawnTask) {
        super(spawnTask);
    }

    public SpawnResult spawn(Player player, Position pos, Level level) {
        SpawnResult result = SpawnResult.OK;

        int blockId = level.getBlockIdAt((int) pos.x, (int) pos.y, (int) pos.z);
        int biomeId = level.getBiomeId((int) pos.x, (int) pos.z);

        if (blockId != Block.GRASS) {
            result = SpawnResult.WRONG_BLOCK;
        } else if (pos.y > 127 || pos.y < 1 || level.getBlockIdAt((int) pos.x, (int) pos.y, (int) pos.z) == Block.AIR) {
            result = SpawnResult.POSITION_MISMATCH;
        } else if (biomeId == Biome.HELL || level.getName().equals("end")) {
            result = SpawnResult.WRONG_BLOCK;
        } else {
            this.spawnTask.createEntity(getEntityName(), pos.add(0, 2.3, 0));
        }

        return result;
    }

    @Override
    public int getEntityNetworkId() {
        return EntityCow.NETWORK_ID;
    }

    @Override
    public String getEntityName() {
        return "Cow";
    }
}
