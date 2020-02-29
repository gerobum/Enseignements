/*
 * Copyright (C) 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.uha.miage.jpa;

import edu.uha.miage.metier.Person;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByNom(String nom);
    List<Person> findByAge(int age);

    Person findById(long id);
}
