/*
 * @author: William J. Horn
 * github: https://github.com/william-horn
 */
package satchel.gui.widgets;

import javax.swing.JComponent;
import javax.swing.JPanel;

import satchel.gui.interfaces.ContainerInitializer;
import satchel.gui.interfaces.WidgetListener;
import satchel.math.vectors.Vector2;
import satchel.shared.interfaces.VoidGenericCallback;
import satchel.shared.util.EventSignal;

import java.awt.Color;

@SuppressWarnings("unused")
public class Container extends Widget<JPanel> {
	public Container() {
		super(new JPanel());
	}

	public Container(Widget<?> parent) {
		super(new JPanel(), parent);
	}
}
