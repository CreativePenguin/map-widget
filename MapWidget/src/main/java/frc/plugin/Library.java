package frc.plugin;

import edu.wpi.first.shuffleboard.api.plugin.Plugin;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;


@Description(group = "Stuypulse 694", name = "Field Map")

public class Library extends Plugin {
    public boolean someLibraryMethod() {
        return true;
    }
    
    public List<ComponentType> getComponents() {
        return ImmutableList.of(WidgetType.forAnnotatedWidget(MyWidget.class));
    }
}
