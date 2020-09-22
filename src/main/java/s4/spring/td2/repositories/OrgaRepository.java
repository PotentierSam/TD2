package s4.spring.td2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.td2.models.Organization;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {
	public Optional<Organization> findByName(String name);
}
