/*-------------------------------------------------------------------------------------------------
 - #%L                                                                                            -
 - protocol-model                                                                                 -
 - %%                                                                                             -
 - Copyright (C) 2016 - 2018 République et Canton de Genève                                       -
 - %%                                                                                             -
 - This program is free software: you can redistribute it and/or modify                           -
 - it under the terms of the GNU Affero General Public License as published by                    -
 - the Free Software Foundation, either version 3 of the License, or                              -
 - (at your option) any later version.                                                            -
 -                                                                                                -
 - This program is distributed in the hope that it will be useful,                                -
 - but WITHOUT ANY WARRANTY; without even the implied warranty of                                 -
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the                                   -
 - GNU General Public License for more details.                                                   -
 -                                                                                                -
 - You should have received a copy of the GNU Affero General Public License                       -
 - along with this program. If not, see <http://www.gnu.org/licenses/>.                           -
 - #L%                                                                                            -
 -------------------------------------------------------------------------------------------------*/

package ch.ge.ve.protocol.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;

/**
 * This model class represents a candidate, along with their description
 */
@SuppressWarnings("unused")
public final class Candidate implements Serializable {
  /**
   * Use this field to determine the candidate identifier.
   * When this is used with eCH interfaces, it should contain the eCH-0155:candidateIdentification value for elections,
   * and a representation of an answer for votations (for instance concatenating the eCH-0155:questionIdentification and
   * the index of the corresponding answer).
   * Similarly, candidates corresponding to lines left blank should be represented with a non-equivocal description,
   * <em>e.g.</em> <tt>blank-line-17</tt>.
   */
  private final String candidateDescription;

  @JsonCreator
  public Candidate(@JsonProperty("candidateDescription") String candidateDescription) {
    this.candidateDescription = candidateDescription;
  }

  public String getCandidateDescription() {
    return candidateDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Candidate candidate = (Candidate) o;
    return Objects.equals(candidateDescription, candidate.candidateDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(candidateDescription);
  }
}
