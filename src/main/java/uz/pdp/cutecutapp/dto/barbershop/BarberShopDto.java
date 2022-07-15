package uz.pdp.cutecutapp.dto.barbershop;

import uz.pdp.cutecutapp.dto.GenericDto;

import java.util.List;

public class BarberShopDto extends GenericDto {

    public String name;

    public String description;

    public String workingTime;

    public String address;

    public Double latitude;

    public Double longitude;

    public boolean isClosed;

    public Double rating;

    public Double distance;

    public List<String> pictures;
}
