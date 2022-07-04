package com.sd.forvodafone.service;

import com.sd.forvodafone.model.Slot;
import com.sd.forvodafone.model.Ticket;
import com.sd.forvodafone.repository.ISlotRepository;
import com.sd.forvodafone.repository.ITicketRepository;
import com.sd.forvodafone.repository.IVehicleTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class TicketService implements ITicketService{

    @Autowired
    private ISlotRepository slotRepository;

    @Autowired
    private IVehicleTypesRepository vehicleTypesRepository;

    @Autowired
    private ITicketRepository ticketRepository;


    @Override
    public String getStatus(){
        String response="";
        response="Status:\n";
        List<Ticket> ticketList= ticketRepository.findByStatus(1);
        for (Ticket ticket : ticketList)
        {
            response+=ticket.getPlate()+" "+ticket.getColour()+" "+"["+ticket.getSlots()+"]\n";
        }
        return response;

    };

    @Override
    public String parkOut(String input)
    {
        String response="";
        var ticketNo=Long.parseLong(input.split("\\s+")[1]);
        Ticket ticket= ticketRepository.findById(ticketNo).get();
        ticket.setStatus(2);
        ticketRepository.save(ticket);
        var slots= ticket.getSlots().split("\\s+");
        for (String var : slots)
        {
            Slot s=slotRepository.findById(Long.parseLong(var)).get();
            s.setStatus(0);
            slotRepository.save(s);
        }
        return response;
    }




    @Override
    public String park(String input)
    {
        String response="";
        List<Long> slotListForUpdate=new ArrayList<>();
        var vehicleTypeName=input.split("\\s+")[3];
        var vehicleColour=input.split("\\s+")[2];
        var vehiclePlate=input.split("\\s+")[1];
        var emptySlots = slotRepository.findByStatus(0);
        var vehicleType=vehicleTypesRepository.findByName(vehicleTypeName).get(0);
        var vehicleSize=Integer.parseInt(vehicleType.getSlotsize());
        if(emptySlots.stream().count()==0)
        {
            response="Garage is full.";
            return  response;
        }
        if(emptySlots.stream().count()<vehicleSize)
        {
            response="Garage is full.";
            return  response;
        }
        if(vehicleSize==1)
        {
            for(int i=0;i<emptySlots.stream().count();i++) {
                if((emptySlots.get(i+1).getId()-emptySlots.get(i).getId())==1)
                {
                    var e=emptySlots.get(i);
                    e.setStatus(1);
                    slotRepository.save(e);
                    e=emptySlots.get(i+1);
                    e.setStatus(2);
                    slotRepository.save(e);
                    response="Allocated 1 slot.";
                    Ticket ticket=new Ticket();
                    ticket.setStatus(1);
                    ticket.setColour(vehicleColour);
                    ticket.setPlate(vehiclePlate);
                    ticket.setSlots(emptySlots.get(i).getId().toString()+" "+(emptySlots.get(i).getId()+1));
                    ticketRepository.save(ticket);
                    return  response;

                }


            }
        }
        if(vehicleSize==2)
        {
            for(int i=0;i<emptySlots.stream().count();i++) {
                if(((emptySlots.get(i+1).getId()-emptySlots.get(i).getId())==1)&&((emptySlots.get(i+2).getId()-emptySlots.get(i+1).getId())==1))
                {
                    var e=emptySlots.get(i);
                    e.setStatus(1);
                    slotRepository.save(e);
                    e=emptySlots.get(i+1);
                    e.setStatus(1);
                    slotRepository.save(e);
                    e=emptySlots.get(i+2);
                    e.setStatus(2);
                    slotRepository.save(e);
                    response="Allocated 2 slot.";
                    Ticket ticket=new Ticket();
                    ticket.setStatus(1);
                    ticket.setColour(vehicleColour);
                    ticket.setPlate(vehiclePlate);
                    ticket.setSlots(emptySlots.get(i).getId().toString()+" "+emptySlots.get(i+1).getId().toString()+" "+(emptySlots.get(i+1).getId()+1));
                    ticketRepository.save(ticket);
                    return  response;

                }


            }
        }
        if(vehicleSize==4)
        {
            for(int i=0;i<emptySlots.stream().count();i++) {
                if(((emptySlots.get(i+1).getId()-emptySlots.get(i).getId())==1)&&((emptySlots.get(i+2).getId()-emptySlots.get(i+1).getId())==1)
                &&((emptySlots.get(i+3).getId()-emptySlots.get(i+2).getId())==1)&&((emptySlots.get(i+4).getId()-emptySlots.get(i+3).getId())==1))
                {
                    var e=emptySlots.get(i);
                    e.setStatus(1);
                    slotRepository.save(e);
                    e=emptySlots.get(i+1);
                    e.setStatus(1);
                    slotRepository.save(e);
                    e=emptySlots.get(i+2);
                    e.setStatus(1);
                    slotRepository.save(e);
                    e=emptySlots.get(i+3);
                    e.setStatus(1);
                    slotRepository.save(e);
                    e=emptySlots.get(i+4);
                    e.setStatus(2);
                    slotRepository.save(e);
                    response="Allocated 4 slot.";
                    Ticket ticket=new Ticket();
                    ticket.setStatus(1);
                    ticket.setColour(vehicleColour);
                    ticket.setPlate(vehiclePlate);
                    ticket.setSlots(emptySlots.get(i).getId().toString()+" "+emptySlots.get(i+1).getId().toString()+" "+emptySlots.get(i+2).getId().toString()+" "+emptySlots.get(i+3).getId().toString()+" "+(emptySlots.get(i+3).getId()+1));
                    ticketRepository.save(ticket);
                    return  response;

                }


            }
        }

        return  response;

    }

}
