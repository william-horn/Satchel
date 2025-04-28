/*
 * @author: William J. Horn
 * github: https://github.com/william-horn
 */
package satchel.gui.widgets;

import javax.swing.JFrame;

import satchel.gui.abstracts.SuperWidget;
import satchel.gui.interfaces.WidgetListener;
import satchel.gui.interfaces.WindowInitializer;
import satchel.math.vectors.UDim2;
import satchel.math.vectors.Unit2;
import satchel.shared.interfaces.VoidGenericCallback;
import satchel.shared.util.EventSignal;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Toolkit;

@SuppressWarnings("unused")
public class Window extends SuperWidget<JFrame> {
	public static Unit2 screenSize = new Unit2(
			(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
			(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());

	public Window() {
		super(new JFrame());
		JFrame ref = this.getRef();
		ref.setTitle("Unnamed");
		ref.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ref.setLayout(null);

		// call computeSize() before setLocationRelativeToNull
		// this.computeSize();
		this.setTransformSize(1, 0, 1, 0);
		ref.setLocationRelativeTo(null);
		ref.setVisible(true);
	}

	@Override
	public void add(Widget<?> widget) {
		super.add(widget);
		this.getRef().add(widget.getRef());
	}

	@Override
	public Unit2 computeTransformSize() {
		UDim2 transformSize = this.getTransformSize();
		return transformSize.toComputedComponents(screenSize);
	}

	@Override
	public Unit2 computeMaxSize() {
		return this.getMaxSize().toComputedComponents(screenSize);
	}

	@Override
	public Unit2 computeMinSize() {
		return this.getMinSize().toComputedComponents(screenSize);
	}
}
