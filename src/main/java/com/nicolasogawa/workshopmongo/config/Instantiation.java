package com.nicolasogawa.workshopmongo.config;

import com.nicolasogawa.workshopmongo.domain.Post;
import com.nicolasogawa.workshopmongo.domain.User;
import com.nicolasogawa.workshopmongo.dto.AuthorDTO;
import com.nicolasogawa.workshopmongo.dto.CommentDTO;
import com.nicolasogawa.workshopmongo.repository.PostRepository;
import com.nicolasogawa.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User mingau = new User(null, "Mingau", "mingau@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, mingau));

        Post post1 = new Post(null, sdf.parse("21/03/2026"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2026"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(mingau));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("22/03/2026"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2026"), new AuthorDTO(mingau));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2026"), new AuthorDTO(maria));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1));
        userRepository.save(maria);
        mingau.getPosts().addAll(Arrays.asList(post2));
        userRepository.save(mingau);

    }
}
