package org.example.test.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.example.test.demo.DataTable;
import org.example.test.demo.DataTableContainerView;
import org.example.test.demo.Tree;
import org.example.test.demo.TreeContainerView;
import org.example.test.views.configuration.ContainerViewConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationViewConfiguration {

	private List<ContainerViewConfiguration> containerViewConfigurations;
	
	public ApplicationViewConfiguration() {
		// TODO Auto-generated constructor stub
		this.containerViewConfigurations = new ArrayList<ContainerViewConfiguration>();
		this.containerViewConfigurations.add(new ContainerViewConfiguration(JButton.class, "toolbar.button.demo.1.text", "toolbar.button.demo.1.tool.tip", DataTable.class, DataTableContainerView.class, false));
		this.containerViewConfigurations.add(new ContainerViewConfiguration(JButton.class, "toolbar.button.demo.2.text", "toolbar.button.demo.2.tool.tip", Tree.class, TreeContainerView.class, true));
		this.containerViewConfigurations.add(new ContainerViewConfiguration(JToolBar.Separator.class, null, null, null, null, false));
	}
	
	public void load(String[] packages) {
		log.debug("Loading view configuration from annotated class...");
		// TODO Auto-generated method stub
	}

	public List<ContainerViewConfiguration> getContainerViews() {
		// TODO Auto-generated method stub
		return containerViewConfigurations;
	}
	
}
