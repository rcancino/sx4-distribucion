// Place your Spring DSL code here
import org.springframework.jdbc.core.JdbcTemplate

beans = {

	importacionJdbcTemplate(JdbcTemplate){
		dataSource=ref('dataSource_importacion')
	}
}
