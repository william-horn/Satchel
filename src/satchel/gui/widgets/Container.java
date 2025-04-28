/*
 * @author: William J. Horn
 * github: https://github.com/william-horn
 */
package satchel.gui.widgets;

import javax.swing.JComponent;
import javax.swing.JPanel;

import satchel.gui.abstracts.SuperWidget;
import satchel.gui.interfaces.ContainerInitializer;
import satchel.gui.interfaces.WidgetListener;
import satchel.math.vectors.Unit2;
import satchel.math.vectors.Vector2;
import satchel.shared.interfaces.VoidGenericCallback;
import satchel.shared.util.Console;
import satchel.shared.util.EventSignal;

import java.awt.Color;

@SuppressWarnings("unused")
public class Container extends Widget<JPanel> {
	public Container() {
		super(new JPanel());
		JPanel ref = this.getRef();
		ref.setBackground(Color.RED);
	}

	public Container(SuperWidget<?> parent) {
		this();
		this.setParent(parent);
		this.computeSize();
	}

	@Override
	public Unit2 computeNoLayoutSize() {
		if (!this.hasParent()) {
			throw new Error("Cannot compute size of widget with no parent");
		}
		return super.computeNoLayoutSize();
	}
}
