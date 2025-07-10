package TypeAdapter;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateTypeAdapter extends com.google.gson.TypeAdapter<LocalDate> {
	private final java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

	@Override
	public void write(com.google.gson.stream.JsonWriter out, LocalDate date) throws IOException {
		out.value(date != null ? formatter.format(date) : null);
	}

	@Override
	public LocalDate read(com.google.gson.stream.JsonReader in) throws IOException {
		return in.hasNext() ? LocalDate.parse(in.nextString(), formatter) : null;
	}
}