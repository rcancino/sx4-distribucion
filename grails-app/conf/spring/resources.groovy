// Place your Spring DSL code here
import org.springframework.jdbc.core.JdbcTemplate

import com.luxsoft.sx4.distribucion.*

beans = {

	importacionJdbcTemplate(JdbcTemplate){
		dataSource=ref('dataSource_importacion')
	}

	notificaciones(Notificaciones){}
}
