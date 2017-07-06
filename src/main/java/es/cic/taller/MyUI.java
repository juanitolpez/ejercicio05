package es.cic.taller;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        final TextField surname = new TextField();
        final TextField age = new TextField();
        final TextField address = new TextField();
        
        name.setCaption("Type your name here:");
        name.setPlaceholder("Tu nombre");
        name.setMaxLength(30);
        updateCaption(name, 0);
        name.addValueChangeListener(event -> updateCaption(name, event.getValue().length()));
        
        
       
        /*name.addValueChangeListener(event -> Notification.show("Value changed:",
                String.valueOf(event.getValue()),
                Type.TRAY_NOTIFICATION));*/
        
        surname.setCaption("Type your surname here:");
        surname.setPlaceholder("Tu apellido");
        surname.setMaxLength(30);
        updateCaption(surname, 0);
        surname.addValueChangeListener(event -> updateCaption(surname, event.getValue().length()));
        /*surname.addValueChangeListener(event -> Notification.show("Value changed:",
                String.valueOf(event.getValue()),
                Type.TRAY_NOTIFICATION));*/
        
        age.setCaption("Type your age");
        age.setPlaceholder("25");
        age.setMaxLength(3);
        updateCaption(age, 0);
        age.addValueChangeListener(event -> updateCaption(age, event.getValue().length()));
        
        address.setCaption("Type your address");
        address.setPlaceholder("Tu calle 25, 2D");
        address.setMaxLength(50);
        updateCaption(address, 0);
        address	.addValueChangeListener(event -> updateCaption(address, event.getValue().length()));
        
        Button button = new Button("Click Me");
        button.addClickListener( e -> {
        	
        	name.setValue("Tu_Nombre");
        	
            /*layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));*/
        });
        
        Button miBoton = new Button("Este boton si que no hace nada");
        miBoton.addClickListener(
			
			event ->
			name.setValue("Your Name")
				/*layout.addComponent(new Label("Thanks " + name.getValue()))*/
			
		);
        
        layout.addComponents(name, surname, age, address);
        
        setContent(layout);
        
        
    }
    
    private void updateCaption(final TextField string, final int textLength) {
    	final StringBuilder builder = new StringBuilder();
    	builder.append(String.valueOf(textLength));
    	if (string.getMaxLength()>=0)
    		builder.append("/").append(string.getMaxLength());
    	builder.append(" characters");
    	string.setCaption(builder.toString());
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
