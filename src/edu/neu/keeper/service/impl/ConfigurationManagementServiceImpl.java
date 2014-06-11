package edu.neu.keeper.service.impl;

import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.Stateless;

import edu.neu.keeper.service.ConfigurationManagementService;
import edu.neu.keeper.service.VersioningException;

@Stateless
public class ConfigurationManagementServiceImpl implements
		ConfigurationManagementService {

	@Override
	public String commitModel(String uri, OutputStream model)
			throws VersioningException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createModel(String uri, OutputStream model)
			throws VersioningException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteModel(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getModel(String uri, String version,
			InputStream modelInputStream) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getModel(String uri, InputStream modelInputStream) {
		// TODO Auto-generated method stub

	}

}
