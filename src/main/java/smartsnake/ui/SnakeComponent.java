package smartsnake.ui;

import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import javax.swing.*;
import java.awt.*;

public class SnakeComponent extends JComponent {

    private Garden garden;

    public SnakeComponent(Garden garden) {
        this.garden = garden;
    }

    /**
     * Calls garden.tick() then draws the Garden, Snake and Food
     * @param g the <code>Graphics</code> object to protect
     */
    @Override public void paintComponent(Graphics g) {

    }

    private void drawFood(Graphics g, Food food) {

    }

    private void drawGarden(Graphics g, Garden garden) {

    }

    private void drawSnake(Graphics g, Snake snake) {

    }

}
