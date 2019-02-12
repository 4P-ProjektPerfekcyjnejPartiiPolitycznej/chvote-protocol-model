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
import java.util.List;

/**
 * Model class containing the definition of a set of elections, including the printing authority public key necessary
 * for encrypting communication to the printing authorities
 */
@SuppressWarnings("unused")
public final class ElectionSetWithPublicKey extends ElectionSet<PrintingAuthorityWithPublicKey> {
  @JsonCreator
  public ElectionSetWithPublicKey(@JsonProperty("elections") List<Election> elections,
                                  @JsonProperty("candidates") List<Candidate> candidates,
                                  @JsonProperty(
                                      "printingAuthorities") List<PrintingAuthorityWithPublicKey> printingAuthorities,
                                  @JsonProperty("voterCount") long voterCount,
                                  @JsonProperty("countingCircleCount") int countingCircleCount) {


    super(elections, candidates, printingAuthorities, voterCount, countingCircleCount);
  }
}
