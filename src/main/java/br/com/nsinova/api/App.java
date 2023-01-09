package br.com.nsinova.api;

import br.com.nsinova.allus.ws.rest.AutorizacaoFiltro;
import br.com.nsinova.comum.webservice.rest.FiltroCORS;
import br.com.nsinova.comum.webservice.rest.MapeadorGenericoExcecao;
import br.com.nsinova.comum.webservice.rest.MapeadorObjetoResolvedorContexto;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class App extends ResourceConfig {

    public App() {
        packages("br.com.nsinova.api.controller");
        register(JacksonFeature.class);
        register(MapeadorGenericoExcecao.class);
        register(AutorizacaoFiltro.class);
        register(FiltroCORS.class);
        Map<String, String> opcoes = new HashMap<>();
        opcoes.put("mostrar_atributo_nulo", "N");
        opcoes.put("formato_data", "yyyy-MM-dd");
        register(new MapeadorObjetoResolvedorContexto(opcoes));
    }

}
