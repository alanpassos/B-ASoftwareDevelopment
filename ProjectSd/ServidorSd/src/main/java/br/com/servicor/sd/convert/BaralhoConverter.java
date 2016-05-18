package br.com.servicor.sd.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


import org.apache.commons.lang3.StringUtils;

import br.com.servicor.sd.model.Baralho;
import br.com.servicor.sd.repository.Baralhos;
import br.com.servicor.sd.util.jpa.CDIServiceLocator;



@FacesConverter(forClass = Baralho.class)
public class BaralhoConverter implements Converter {

	private Baralhos baralhos;

	public BaralhoConverter() {
		baralhos = CDIServiceLocator.getBean(Baralhos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			return baralhos.porId(id);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if (value != null && !value.equals("")) {
			Baralho bloco = (Baralho) value;
			
			return bloco.getId() == null ? null : bloco.getId().toString();
		}

		return null;
	}

}
