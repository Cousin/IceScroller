package net.blancodev.icescroller;

import net.blancodev.icescroller.render.EntityManager;
import net.blancodev.icescroller.render.entity.team.PlayerEntity;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ICKeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        PlayerEntity playerEntity = EntityManager.getEntityManager().getPlayerEntity();

        if (GameState.GAME_STATE == GameState.GAME) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                playerEntity.moveLeft();
            } else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                playerEntity.moveRight();
            }
        } else if (GameState.GAME_STATE == GameState.START) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                GameState.GAME_STATE = GameState.GAME;
            }
        }

    }
}
