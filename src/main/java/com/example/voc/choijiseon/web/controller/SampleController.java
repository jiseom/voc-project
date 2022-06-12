package com.example.voc.choijiseon.web.controller;

import com.example.voc.choijiseon.domain.ClientCompany;
import com.example.voc.choijiseon.domain.DeliveryDriver;
import com.example.voc.choijiseon.domain.Manager;
import com.example.voc.choijiseon.domain.TransportCompany;
import com.example.voc.choijiseon.domain.repository.ClientCompanyRepository;
import com.example.voc.choijiseon.domain.repository.DeliveryDriverRepository;
import com.example.voc.choijiseon.domain.repository.ManagerCompanyRepository;
import com.example.voc.choijiseon.domain.repository.TransportCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@RequestMapping("/get")
@RestController
public class SampleController {
    //임의의 데이터 생성 핸들러
    private final TransportCompanyRepository transportCompanyRepository;
    private final DeliveryDriverRepository deliveryDriverRepository;
    private final ClientCompanyRepository clientCompanyRepository;
    private final ManagerCompanyRepository managerCompanyRepository;

    //운송회사 임의 정보 리스트
    @GetMapping("/transport-company")
    public List<TransportCompany> getTransportCompanyList() {
        List<TransportCompany> transportCompanyList = IntStream.range(1, 10)
                .mapToObj(e -> new TransportCompany(1L + e, "운송 회사" + e, "000-000" + e, "TRANSPORT_COMPANY"))
                .collect(Collectors.toList());
        return transportCompanyRepository.saveAll(transportCompanyList);

    }

    //운송기사 임의 정보 리스트
    @GetMapping("/drivers")
    public List<DeliveryDriver> getDeliveryDrivers() {
        List<TransportCompany> transportCompanyList = transportCompanyRepository.findAll();
        List<DeliveryDriver> deliveryDriverList = IntStream.range(1, 10)
                .mapToObj(e -> new DeliveryDriver(1L + e, "운송기사" + e, "010-0000-000" + e, (e * 10000) + 3000000, transportCompanyList.get(e - 1)))
                .collect(Collectors.toList());
        return deliveryDriverRepository.saveAll(deliveryDriverList);
    }

    //고객사 임의 정보 리스트
    @GetMapping("/client-company")
    public List<ClientCompany> getClientCompanyList() {
        List<ClientCompany> clientCompany = IntStream.range(1, 10)
                .mapToObj(e -> new ClientCompany(1L + e, "고객사" + e, "000-000" + e, "CLIENT_COMPANY"))
                .collect(Collectors.toList());
        return clientCompanyRepository.saveAll(clientCompany);
    }

    //고객사 담당자 임의정보 리스트
    @GetMapping("/managers")
    public List<Manager> getManagers() {
        List<ClientCompany> clientCompanies = clientCompanyRepository.findAll();
        List<Manager> managers = IntStream.range(1, 10)
                .mapToObj(e -> new Manager(1L + e, "고객사 담당자" + e, "000-000" + e, clientCompanies.get(e - 1)))
                .collect(Collectors.toList());
        return managerCompanyRepository.saveAll(managers);
    }


}
