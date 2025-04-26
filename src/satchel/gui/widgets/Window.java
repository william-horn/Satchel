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

@SuppressWarnings("unused")
public class Window extends JFrame implements WidgetListener {
	private EventSignal onResizeEvent = new EventSignal();

	public Window(String windowName, int x, int y) {
		this.setTitle(windowName);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(x, y);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				onResizeEvent.fire(e);
			}
		});
	}

	public Window(String windowName) {
		this(windowName, 400, 400);
	}

	public Window(int x, int y) {
		this("Untitled", x, y);
	}

	public Window(WindowInitializer init) {
		this();
		init.init(this);
	}

	public Window() {
		this("Untitled");
	}

	@Override
	public void onResize(VoidGenericCallback f) {
		onResizeEvent.connect(f);
	}
}
