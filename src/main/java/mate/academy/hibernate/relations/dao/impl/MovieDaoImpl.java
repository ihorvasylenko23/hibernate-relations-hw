package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie entity) {
        try {
            Session session = factory.openSession();
            session.beginTransaction();
            Long entityId = (Long) session.save(entity);
            session.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            throw new DataProcessingException("Error while adding entity: " + entity, e);
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        try {
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Movie movie = session.get(Movie.class, id);
            if (movie != null) {
                movie.getActors().size();
            }
            transaction.commit();
            return Optional.ofNullable(movie);
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting entity with id " + id, e);
        }
    }
}
