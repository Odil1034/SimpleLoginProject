package uz.pdp.SimpleLoginProject.dtos;

public record UserCreateDto(
        String username,
        String password,
        String fullName
) {
}
