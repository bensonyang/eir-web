package com.morningsidevc.utils;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;
import org.springframework.web.context.support.ServletContextResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author float.lu
 */
public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private Resource[] locations;
    private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

    @Override
    public void setPropertiesPersister(PropertiesPersister propertiesPersister) {
        super.setPropertiesPersister(propertiesPersister);
        this.propertiesPersister =
                (propertiesPersister != null ? propertiesPersister : new DefaultPropertiesPersister());
    }


    public void setLocations(Resource... locations) {
        this.locations = locations;
    }

    @Override
    protected void loadProperties(Properties props) throws IOException {
        if (this.locations != null) {
            Resource location = this.locations[0];
            String path = ((ServletContextResource) location).getPath();
            try {
                fillProperties(props, new FileSystemResource(path), this.propertiesPersister);
            }
            catch (IOException ex) {
                try {
                    super.setLocations(new Resource[]{this.locations[1]});
                    super.loadProperties(props);
                }catch (Exception e){
                    throw ex;
                }
            }

        }
    }
    static void fillProperties(Properties props, FileSystemResource resource, PropertiesPersister persister)
            throws IOException {


        InputStream stream = resource.getInputStream();
        try {
            stream = resource.getInputStream();
            persister.load(props, stream);
        }
        finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}
