/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t0ast.parkour.visualization;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.t0ast.parkour.training.ParkourEntity;
import com.t0ast.parkour.training.ParkourEnvironment;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

/**
 *
 * @author T0astBread
 */
public class VisualizationPanel extends javax.swing.JPanel
{

    private static final int MARGIN = 10;
    private static final BasicStroke ENTITY_BODY = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND), ENTITY_BG = new BasicStroke(7, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private ParkourEnvironment environment;
    private List<ParkourEntity> entities;
    private float zoom = 2f;
    private int sleepTime = 2;

    /**
     * Creates new form VisualizationPanel
     */
    public VisualizationPanel()
    {
        initComponents();
    }

    public void drawEnvironment(ParkourEnvironment environment, List<ParkourEntity> entities)
    {
        this.environment = environment;
        this.entities = entities;
        repaint();
    }
    
    public void drawEnvironmentUpdateStep(ParkourEnvironment environment, List<ParkourEntity> entities)
    {
        drawEnvironment(environment, entities);
        try
        {
            Thread.sleep(this.sleepTime);
        }
        catch(InterruptedException iex)
        {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        if(this.environment == null)
        {
            return;
        }
        g.setColor(Color.LIGHT_GRAY); //Draw environment
        g.fillRect(MARGIN, MARGIN, zoom(this.environment.getWidth()), zoom(this.environment.getHeight()));

        g.setColor(Color.BLACK); //Draw obstacles
//        //Convert LibGDX polygons to Java Swing polygons
//        Arrays.stream(this.environment.getObstacles()).forEach(o ->
//        {
//            float[] verts = o.getTransformedVertices();
//            int[] xPoints = new int[verts.length / 2], yPoints = new int[verts.length / 2];
//            for(int i = 0; i < verts.length; i++)
//            {
//                int[] toPutIn = yPoints;
//                if(i % 2 == 0)
//                {
//                    toPutIn = xPoints;
//                }
//                toPutIn[i / 2] = project(verts[i]);
//            }
//            g.fillPolygon(xPoints, yPoints, xPoints.length);
//        });
        for(Rectangle obstacles : this.environment.getObstacles())
        {
            g.fillRect(project(obstacles.x), project(obstacles.y), project(obstacles.width), project(obstacles.height));
        }

        if(this.entities == null)
        {
            return;
        }
        //Draw entities
        this.entities.forEach(e ->
        {
//            g.fillOval(MARGIN + zoom(e.getPosition().x), MARGIN + zoom(e.getPosition().y), 5, 5);
            Vector2 endPoint = e.getPosition().cpy().add(e.getDirection().cpy().nor());
            int endX = project(endPoint.x), endY = project(endPoint.y);

            g2d.setStroke(ENTITY_BG);
            g.setColor(Color.BLACK);
            drawEntityLine(g, e, endX, endY);
            g2d.setStroke(ENTITY_BODY);
            g.setColor(Color.YELLOW);
            drawEntityLine(g, e, endX, endY);
            g2d.setStroke(ENTITY_BODY);

            endX -= 2;
            endY -= 2;
            g.setColor(Color.BLACK);
            g.drawOval(endX - 1, endY - 1, 7, 7);
            g.setColor(Color.RED);
            g.drawOval(endX, endY, 5, 5);
        });
    }

    private void drawEntityLine(Graphics g, ParkourEntity e, int endX, int endY)
    {
        g.drawLine(project(e.getPosition().x), project(e.getPosition().y), endX, endY);
    }

    private int zoom(float value)
    {
        return (int) (value * this.zoom);
    }

    private int project(float value)
    {
        return zoom(value) + MARGIN;
    }

    public void setZoom(float zoom)
    {
        this.zoom = zoom;
        if(this.environment != null)
        {
            int w = zoom(this.environment.getWidth()) + 2 * MARGIN;
//            if(getWidth() >= w) w = getWidth();
            int h = zoom(this.environment.getHeight()) + 2 * MARGIN;
//            if(getHeight() >= h) h = getHeight();
            setPreferredSize(new Dimension(w, h));
        }
        repaint();
    }

    public void setSleepTime(int sleepTime)
    {
        this.sleepTime = sleepTime;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
