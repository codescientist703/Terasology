/*
 * Copyright 2016 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.coresamplegameplay;

import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.inventory.InventoryComponent;
import org.terasology.logic.inventory.InventoryManager;
import org.terasology.logic.inventory.ItemComponent;
import org.terasology.logic.players.event.OnPlayerSpawnedEvent;
import org.terasology.registry.In;
import org.terasology.world.block.BlockManager;
import org.terasology.world.block.items.BlockItemFactory;

@RegisterSystem(RegisterMode.AUTHORITY)
public class PlayerStartingInventorySystem extends BaseComponentSystem {
    @In
    BlockManager blockManager;
    @In
    InventoryManager inventoryManager;
    @In
    EntityManager entityManager;

    @ReceiveEvent(components = InventoryComponent.class)
    public void onPlayerSpawnedEvent(OnPlayerSpawnedEvent event, EntityRef player) {
        BlockItemFactory blockFactory = new BlockItemFactory(entityManager);
        // Goodie chest
        EntityRef chest = blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:chest"));
        chest.addComponent(new InventoryComponent(30));

        inventoryManager.giveItem(chest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("Core:companion"), 99));
        inventoryManager.giveItem(chest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:Brick:engine:stair"), 99));
        inventoryManager.giveItem(chest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:Tnt"), 99));
        inventoryManager.giveItem(chest, EntityRef.NULL, entityManager.create("CoreItems:fuseShort"));
        inventoryManager.giveItem(chest, EntityRef.NULL, entityManager.create("CoreItems:fuseLong"));
        inventoryManager.giveItem(chest, EntityRef.NULL, entityManager.create("CoreItems:shallowRailgunTool"));
        inventoryManager.giveItem(chest, EntityRef.NULL, entityManager.create("CoreItems:thoroughRailgunTool"));

        inventoryManager.giveItem(chest, EntityRef.NULL, entityManager.create("CoreItems:railgunTool"));

        inventoryManager.giveItem(chest, EntityRef.NULL, entityManager.create("CoreItems:mrbarsack"));
        inventoryManager.giveItem(chest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:Brick"), 99));
        inventoryManager.giveItem(chest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:Ice"), 99));
        inventoryManager.giveItem(chest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:Plank"), 99));

        EntityRef doorItem = entityManager.create("CoreBlocks:door");
        ItemComponent doorItemComp = doorItem.getComponent(ItemComponent.class);
        doorItemComp.stackCount = 20;
        doorItem.saveComponent(doorItemComp);
        inventoryManager.giveItem(chest, EntityRef.NULL, doorItem);

        // Inner goodie chest
        EntityRef innerChest = blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:Chest"));
        innerChest.addComponent(new InventoryComponent(30));

        inventoryManager.giveItem(innerChest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:lava"), 99));
        inventoryManager.giveItem(innerChest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:water"), 99));

        inventoryManager.giveItem(innerChest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:Iris"), 99));
        inventoryManager.giveItem(innerChest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:Dandelion"), 99));
        inventoryManager.giveItem(innerChest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:Tulip"), 99));
        inventoryManager.giveItem(innerChest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:YellowFlower"), 99));

        // Place inner chest into outer chest
        inventoryManager.giveItem(chest, EntityRef.NULL, innerChest);

        inventoryManager.giveItem(player, EntityRef.NULL, entityManager.create("CoreItems:pickaxe"));
        inventoryManager.giveItem(player, EntityRef.NULL, entityManager.create("CoreItems:axe"));
        inventoryManager.giveItem(player, EntityRef.NULL, entityManager.create("CoreItems:shovel"));
        inventoryManager.giveItem(player, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("CoreBlocks:Torch"), 99));
        inventoryManager.giveItem(player, EntityRef.NULL, entityManager.create("CoreItems:explodeTool"));
        inventoryManager.giveItem(player, EntityRef.NULL, entityManager.create("CoreItems:railgunTool"));
        inventoryManager.giveItem(player, EntityRef.NULL, chest);
    }
}
