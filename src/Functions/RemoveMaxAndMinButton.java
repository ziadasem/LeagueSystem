/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

/**
 *
 * @author hosam
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RemoveMaxAndMinButton extends JDialog {
    public RemoveMaxAndMinButton(JFrame frame, String str) {
        super(frame, str);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        try {
            RemoveMaxAndMinButton frame = new RemoveMaxAndMinButton(new JFrame(), "No min max buttons");
            JPanel panel = new JPanel();
            frame.setUndecorated(true);
            panel.setSize(200, 200);
            JLabel lbl = new JLabel("blah blah");
            panel.add(lbl);
            frame.add(panel);
            frame.setSize(400, 400);
            frame.setVisible(true);
        } catch (IllegalArgumentException e) {
            System.exit(0);
        }
    }
}