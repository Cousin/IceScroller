package net.blancodev.icescroller.render;

import net.blancodev.icescroller.render.entity.team.PlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EntityManager {

    private static final EntityManager SINGLETON = new EntityManager();

    public static EntityManager getEntityManager() {
        return SINGLETON;
    }

    private CopyOnWriteArrayList<Entity> renderedEntities;
    private int idIndex;

    public EntityManager() {
        this.renderedEntities = new CopyOnWriteArrayList<>();
        this.idIndex = 0;
    }

    public void addEntity(Entity entity) {
        this.renderedEntities.add(entity);
        if (!(entity instanceof PlayerEntity)) {
            entity.setId(idIndex);
        }
        idIndex++;
    }

    public void removeEntity(Entity entity) {
        this.renderedEntities.remove(entity);
    }

    public PlayerEntity getPlayerEntity() {
        return (PlayerEntity) this.renderedEntities.get(0);
    }

    public List<Entity> getRenderedEntities() {
        return renderedEntities;
    }
}
