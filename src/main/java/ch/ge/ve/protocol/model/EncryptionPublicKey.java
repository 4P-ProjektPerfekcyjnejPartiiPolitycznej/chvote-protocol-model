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

/**
 * This model class holds the value of an encryption public key.
 */
@SuppressWarnings("unused")
public final class EncryptionPublicKey extends PublicKey {

  @JsonCreator
  public EncryptionPublicKey(@JsonProperty("publicKey") BigInteger publicKey,
                             @JsonProperty("encryptionGroup") EncryptionGroup encryptionGroup) {
    super(publicKey, encryptionGroup);
  }

  @Override
  @JsonProperty("encryptionGroup")
  public EncryptionGroup getCyclicGroup() {
    // safe cast (ensured by construction)
    return (EncryptionGroup) super.getCyclicGroup();
  }
}
