package org.example.test.views;

import java.util.ArrayList;
import java.util.List;

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
		this.containerViewConfigurations.add(new ContainerViewConfiguration("toolbar.button.demo.1.text", "toolbar.button.demo.1.tool.tip", DataTable.class, DataTableContainerView.class));
		this.containerViewConfigurations.add(new ContainerViewConfiguration("toolbar.button.demo.2.text", "toolbar.button.demo.2.tool.tip", Tree.class, TreeContainerView.class));
	}
	
	public void load() {
		log.debug("Loading view configuration...");
		// TODO Auto-generated method stub
	}

	public List<ContainerViewConfiguration> getContainerViews() {
		// TODO Auto-generated method stub
		return containerViewConfigurations;
	}
	
}
