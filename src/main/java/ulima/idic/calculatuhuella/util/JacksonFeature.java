package ulima.idic.calculatuhuella.util;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

// import org.glassfish.jersey.CommonProperties;

import com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper;
import com.fasterxml.jackson.jaxrs.base.JsonParseExceptionMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class JacksonFeature implements Feature {

	@Override
	public boolean configure(FeatureContext context) {
		String postfix = '.' + context.getConfiguration().getRuntimeType().name().toLowerCase();

		// context.property(CommonProperties.MOXY_JSON_FEATURE_DISABLE + postfix, true);

		context.register(JsonParseExceptionMapper.class);
		context.register(JsonMappingExceptionMapper.class);
		context.register(JacksonJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);

		return true;
	}

}
