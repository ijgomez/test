package org.example.test.views.toolbar;

import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.example.test.views.ApplicationViewConfiguration;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.buttons.AppButton;
import org.example.test.views.components.buttons.ChangeViewButton;
import org.example.test.views.components.events.ChangeViewEvent;
import org.example.test.views.components.toolbar.AppToolBar;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationToolBar extends AppToolBar implements ApplicationModelListener {

	private static final long serialVersionUID = -95062186306252920L;
	
	public ApplicationToolBar(ApplicationViewConfiguration viewConfiguration) {
		super(viewConfiguration);
	}
	
	protected void initializateGUI() {
		JButton button1, button2, button3, button4, button5;
		
		super.getViewConfiguration().ifPresent((vc) -> {
			if (vc != null && vc.getContainerViews() != null) {
				vc.getContainerViews().forEach((c) -> {
					if (c.getClassElement().equals(JButton.class)) {
						// TODO Auto-generated method stub
						AppButton button;
						button = new ChangeViewButton<>(c.getTitleTextKey(), c.getToolTipTextKey(), c.getClassContainer());
						button.setSelected(c.isSelected());
						super.add(button);		
					} else if (c.getClassElement().equals(Separator.class)) {
						super.add(new Separator());
					}
							
				});
			}
		});
		
		
		
		
		
		button1 = new JButton("Entity 1");
		button2 = new JButton("Entity 2");
		button3 = new JButton("Entity 3");
		button4 = new JButton("Entity 4");
		button5 = new JButton("Entity 5");
		
		super.add(button1);
		super.add(button2);
		super.add(button3);
		super.add(button4);
		super.add(new JPanel());
		super.add(new Separator());
		super.add(button5);
	}
	
	@Override
	protected void registerEventListeners() {
		super.addEventListener(ChangeViewEvent.class, (e) -> selectedButtonAction(((ChangeViewEvent) e)));
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

	private void selectedButtonAction(ChangeViewEvent e) {
		Stream.of(super.getComponents()).forEach((c) -> {
			if (c instanceof ChangeViewButton) {
				ChangeViewButton<?> changeViewButton = (ChangeViewButton<?>) c;
				if (changeViewButton.getClassView().equals(e.getClassEntity())) {
					changeViewButton.setEnabled(true);
					log.trace("enable {}", changeViewButton);
				}
			}
		});
	}
	
}
