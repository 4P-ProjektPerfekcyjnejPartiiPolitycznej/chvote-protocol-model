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
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * Model class representing an encrypted message
 */
public class EncryptedMessage {
  private final BigInteger c1;
  private final byte[]     c2;
  private final byte[]     iv;

  @JsonCreator
  public EncryptedMessage(@JsonProperty("c1") BigInteger c1,
                          @JsonProperty("c2") byte[] c2,
                          @JsonProperty("iv") byte[] iv) {
    this.c1 = c1;
    this.c2 = c2;
    this.iv = iv;
  }

  public BigInteger getC1() {
    return c1;
  }

  public byte[] getC2() {
    return c2;
  }

  public byte[] getIv() {
    return iv;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EncryptedMessage that = (EncryptedMessage) o;
    return c1.compareTo(that.c1) == 0 &&
           Arrays.equals(c2, that.c2) &&
           Arrays.equals(iv, that.iv);
  }

  @Override
  public int hashCode() {
    return Objects.hash(c1, c2);
  }

  @Override
  public String toString() {
    return String.format("EncryptedMessage{c1=%s, c2=%s, iv=%s}", c1, Arrays.toString(c2), Arrays.toString(iv));
  }
}
