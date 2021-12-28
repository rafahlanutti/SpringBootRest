package br.com.springbootrest.estudos.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {

	private DozerMapper() {
		super();
	}

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public static <O, D> D parseObject(O origem, Class<D> destino) {
		return mapper.map(origem, destino);
	}

	public static <O, D> List<D> parseListObjects(List<O> origem, Class<D> destino) {

		List<D> destinations = new ArrayList<>();
		origem.forEach(o -> destinations.add(mapper.map(o, destino)));
		return destinations;
	}

}
