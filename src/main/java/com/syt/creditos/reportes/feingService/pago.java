package com.syt.creditos.reportes.feingService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Map;

@FeignClient(name = "credit-process")
public interface pago {

    @RequestMapping(value = "/detallesPagos/reportePagosFecha",method = RequestMethod.POST)
    ArrayList<Object> generarReporte(@RequestBody Map<String, Object> map);

}
