package satchel.gui.abstracts;

import java.util.ArrayList;

import satchel.gui.widgets.Widget;
import satchel.gui.widgets.Widget.SizeMode;
import satchel.math.vectors.Vector2;

public abstract class SuperWidget {
	private T ref;
	private Widget<?> parent;

	final private Vector2 sizeScale = new Vector2(1, 1);
	final private Vector2 sizeOffset = new Vector2(0, 0);
	final private Vector2 absoluteSize = new Vector2(0, 0);
	private SizeMode sizeMode = SizeMode.TRANSFORM;

	final public ArrayList<Widget<?>> children = new ArrayList<>();
}
