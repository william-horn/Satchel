# Satchel

An opinionated Java framework by [William J. Horn](https://github.com/william-horn).

## Includes:

* A custom GUI framework built on `javax.swing` for interacting with, manipulating, and creating GUI widgets and components with intuitive flexability.

* A `Console` class which includes powerful user input options, dynamic text and background color rendering, debug tools, and benchmark utilities right out the box.

* A shared `util` package containing many useful utility classes, both static and inheritable, for supporting large project needs.

* Vast custom exception handling for easier debugging

* System configuration settings that persist across runtime sessions

* A custom data encoder/decoder mimmicking JSON format for quick high-level object serialization and deserialization. This can be used to save and load application states that are more complex than config settings.

## GUI Capabilities

Satchel's most powerful feature will most likely be the API for creating `swing` components and applications. No built-in layout managers are used in this framework, so all responsive behavior, positioning, sizing, drawing, animating, etc, is made from scratch.

#### Widgets

Widgets are the smallest, most fundamental building blocks of this GUI framework. They are esentially wrapper classes for `swing` components, meaning they do not contain functionality that far beyond what `swing` offers out of the box, aside from some default initialization. For example, this is how you can initialize a widget in Satchel:

#### Example:
```java
import gui.widgets.Container;
import gui.widgets.Window;
import gui.widgets.WindowContainer;

Window window = new Window("Title", 500, 500);
```

This will create a new `JFrame` component (the window screen) with a title of `"Title"`, and sized to be `(500, 500)` in width and height.

If you want to add more initialization settings, or overwrite the default ones defined by Satchel, you can pass a lambda function that initializes before the object is returned from it's constructor. 

#### Example:

```java
import gui.widgets.Container;
import gui.widgets.Window;
import gui.widgets.WindowContainer;

// create a new window with custom initialized settings
Window window = new Window(self -> {
	self.setSize(500, 500);
	self.setBackground(Color.BLUE);
	self.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE));
});

// create a window container and parent it to window
WindowContainer windowContainer = new WindowContainer(window);
```