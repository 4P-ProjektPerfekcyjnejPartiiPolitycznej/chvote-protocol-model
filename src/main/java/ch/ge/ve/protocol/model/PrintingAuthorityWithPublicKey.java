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
import java.util.Objects;

/**
 * This extension of the {@link PrintingAuthority} model includes a public key so that ControlComponents know for
 * whom they should encrypt the private credentials they generate.
 */
@SuppressWarnings("unused")
public class PrintingAuthorityWithPublicKey extends PrintingAuthority {
  private final BigInteger publicKey;

  @JsonCreator
  public PrintingAuthorityWithPublicKey(@JsonProperty("name") String name,
                                        @JsonProperty("publicKey") BigInteger publicKey) {
    super(name);
    // Defensive / Immutable copy?
    this.publicKey = publicKey;
  }

  public BigInteger getPublicKey() {
    return publicKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    PrintingAuthorityWithPublicKey that = (PrintingAuthorityWithPublicKey) o;
    return Objects.equals(publicKey, that.publicKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), publicKey);
  }
}
