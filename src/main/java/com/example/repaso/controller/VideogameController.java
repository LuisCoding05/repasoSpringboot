package com.example.repaso.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.repaso.model.Developer;
import com.example.repaso.model.Genre;
import com.example.repaso.model.Platform;
import com.example.repaso.model.User;
import com.example.repaso.model.Videogame;
import com.example.repaso.repository.IUserDao;
import com.example.repaso.service.DeveloperService;
import com.example.repaso.service.GenreServiceImp;
import com.example.repaso.service.PlatformServiceImp;
import com.example.repaso.service.VideogameServiceImp;


@Controller
@RequestMapping("/videogames")
public class VideogameController {

    @Autowired
    IUserDao userDao;

    @Autowired
    PlatformServiceImp platformService;

    @Autowired
    GenreServiceImp genreService;

    @Autowired
    DeveloperService developerService;

    @Autowired
    VideogameServiceImp videogameServiceImp;
    
    // Este método se ejecutará antes de cada método del controlador
    @ModelAttribute
    public void addUserToModel(Model model, Principal principal) {
        if (principal != null) {
            String emailOrUsername = principal.getName();
            
            // Busca primero por email (asumiendo que el "username" en Spring Security es el email)
            Optional<User> userOptional = userDao.findByUsername(emailOrUsername);
            
            // Si no se encuentra por email, busca por username
            if (!userOptional.isPresent()) {
                userOptional = userDao.findByEmail(emailOrUsername);
            }
            
            // Si encontramos al usuario, lo añadimos al modelo
            userOptional.ifPresent(user -> model.addAttribute("user", user));
        }
    }

    @GetMapping("/list")
    public String list(Model model) {
        // Ya no necesitas buscar el usuario aquí, será agregado automáticamente
        List<Videogame> videogames = videogameServiceImp.list();
        model.addAttribute("videogames", videogames);
        return "videogames/index";
    }
    
    @GetMapping("")
    public String index(Model model) {
        // El usuario ya estará disponible en el modelo
        List<Videogame> videogames = videogameServiceImp.list();
        model.addAttribute("videogames", videogames);
        return "videogames/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        // Busca el videojuego por su ID
        Videogame videogame = videogameServiceImp.findById(id);

        // Si el videojuego no existe, redirige a una página de error
        if (videogame == null) {
            return "redirect:/error"; // O maneja el error como prefieras
        }

        // Agrega el videojuego al modelo para pasarlo a la vista
        model.addAttribute("videogame", videogame);

        // Devuelve el nombre de la vista (por ejemplo, "videogame/show")
        return "videogames/show";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        // Crea un objeto Videogame vacío para el formulario
        List<Developer> developers = developerService.findAll();
        model.addAttribute("developers", developers);

        List<Platform> platforms = platformService.findAll();
        model.addAttribute("platforms", platforms);

        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);

        model.addAttribute("videogame", new Videogame());
        return "videogames/create"; // Vista del formulario de creación
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Videogame videogame) {
        // Guarda el videojuego en la base de datos
        videogameServiceImp.save(videogame);
        return "redirect:/videogames"; // Redirige a la lista de videojuegos
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        // Busca el videojuego por su ID
        Videogame videogame = videogameServiceImp.findById(id);

        if (videogame == null) {
            return "redirect:/error"; // Manejo de error si el videojuego no existe
        }

        List<Developer> developers = developerService.findAll();
        model.addAttribute("developers", developers);

        List<Platform> platforms = platformService.findAll();
        model.addAttribute("platforms", platforms);

        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);

        // Agrega el videojuego al modelo para el formulario de edición
        model.addAttribute("videogame", videogame);
        return "videogames/edit"; // Vista del formulario de edición
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Videogame videogame) {
        // Actualiza el videojuego en la base de datos
        videogameServiceImp.save(videogame);
        return "redirect:/videogames"; // Redirige a la lista de videojuegos
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        videogameServiceImp.deleteById(id);
        return "redirect:/videogames";
    }

    // O mejor aún, usa DELETE HTTP method:
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideogame(@PathVariable Long id) {
        videogameServiceImp.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
