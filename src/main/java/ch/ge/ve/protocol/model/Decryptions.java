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

import ch.ge.ve.protocol.util.Hashable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * Model class holding the decryptions of an authority.
 */
@SuppressWarnings("unused")
public final class Decryptions implements Hashable {
  private final List<BigInteger> decryptionsList;

  @JsonCreator
  public Decryptions(@JsonProperty("decryptionsList") List<BigInteger> decryptionsList) {
    this.decryptionsList = decryptionsList;
  }

  public List<BigInteger> getDecryptionsList() {
    return decryptionsList;
  }

  @Override
  public Object[] elementsToHash() {
    return decryptionsList.toArray();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Decryptions that = (Decryptions) o;
    return Objects.equals(decryptionsList, that.decryptionsList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(decryptionsList);
  }
}
