package TypeAdapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import Biblioteca.model.*;

public class ObraTypeAdapter extends TypeAdapter<Obra> {
	private final Gson gson = new Gson();

	@Override
	public void write(JsonWriter out, Obra obra) throws IOException {
		if (obra == null) {
			out.nullValue();
			return;
		}

		gson.toJson(gson.toJsonTree(obra), out);
	}

	@Override
	public Obra read(JsonReader in) throws IOException {

		Object jsonObject = gson.fromJson(in, Object.class);
		String jsonString = gson.toJson(jsonObject);

		if (jsonString.contains("\"isbn\"")) {
			return gson.fromJson(jsonString, Livro.class);
		} else if (jsonString.contains("\"issn\"")) {
			return gson.fromJson(jsonString, Revista.class);
		} else if (jsonString.contains("\"categoria\"")) {
			return gson.fromJson(jsonString, Artigo.class);
		}

		throw new IllegalArgumentException("Tipo de obra não reconhecido: " + jsonString);
	}
}