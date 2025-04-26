/*
 * @author: William J. Horn
 * github: https://github.com/william-horn
 */
package satchel.gui.widgets;

import javax.swing.JFrame;

import satchel.gui.interfaces.WidgetListener;
import satchel.gui.interfaces.WindowInitializer;
import satchel.shared.interfaces.VoidGenericCallback;
import satchel.shared.util.EventSignal;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Window extends Widget<JFrame> {

	public Window(String windowName, int x, int y) {
		super(new JFrame());
		JFrame ref = new JFrame();
		ref.setTitle(windowName);
		ref.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ref.setLayout(null);
		ref.setSize(x, y);
		ref.setLocationRelativeTo(null);
		ref.setVisible(true);

		ref.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				onResize.fire(e);
			}
		});

		this.ref = ref;
	}

	public Window(String windowName) {
		this(windowName, 400, 400);
	}

	public Window(int x, int y) {
		this("Untitled", x, y);
	}

	public Window() {
		this("Untitled");
	}

	public void add(Widget widget) {
		this.children.add(widget);
		this.ref.add(widget.getRef());
	}
}
