package com.syt.creditos.reportes.feingService;

import com.syt.creditos.reportes.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@FeignClient(name = "credit-process")
public interface pago {

    @RequestMapping(value = "/detallesPagos/reportePagosFecha",method = RequestMethod.POST)
    ArrayList<Object> generarReporte(@RequestBody Map<String, Object> map);

    @RequestMapping(value = "/detallesInteres/creditosCartera",method = RequestMethod.POST)
//    ArrayList<Object> generarReporteCreditosCartera(@RequestBody Integer idUser);
    Map<String, Object> generarReporteCreditosCartera(@RequestBody Map<String, Object> no);

    @RequestMapping(value = "/detallesInteres/interesCarteraFechas",method = RequestMethod.POST)
    public Map<String, Object> generarReporteInteresCarteraFechas(@Valid @RequestBody Map<String, Object> map);

    @RequestMapping(value = "/detallesMoras/reporteMorasDias",method = RequestMethod.POST)
    public Map<String, Object> reporteMorasDias();

    @RequestMapping(value = "/detallesSeguro/seguroCarteraFechas",method = RequestMethod.POST)
    public Map<String, Object> generarReporteSeguroCarteraFechas(@Valid @RequestBody Map<String, Object> map);


}
