package com.kwpugh.gobber2.events;

import com.kwpugh.gobber2.Gobber2;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
    Keeps track of spawned mobs and deletes
    them when their age exceeds the config limit
    Loses track if player logs out in SP or
    server restarts in MP - need to fix
 */

public class  SummonerManager
{
    public static final List<Entity> entityList = new ArrayList<>();

    public static void init()
    {
        ServerTickEvents.END_SERVER_TICK.register(tick -> {
            for(Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();)
            {
                Entity entityToTest = iterator.next();

                if (entityToTest.age > Gobber2.CONFIG.GENERAL.summonerMobLifespan)
                {
                    iterator.remove();
                    entityToTest.remove(Entity.RemovalReason.DISCARDED);
                }
            }
        });
    }

    public static void addToList(Entity entity)
    {
        entityList.add(entity);
    }

    // compares entity's uuid to each in list looking for a match
    public static boolean isInList(Entity entity)
    {
        for(Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();)
        {
            Entity entityToTest = iterator.next();

            if(entityToTest.getUuid() == entity.getUuid()) return true;
        }

        return false;
    }

    // For Debugging
    public static void showList()
    {
        System.out.println("list: " + entityList.toString());
    }
}