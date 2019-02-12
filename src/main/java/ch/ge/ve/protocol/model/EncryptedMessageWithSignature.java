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
import java.util.Objects;

/**
 * Model class representing an encrypted message with its corresponding signature
 */
public class EncryptedMessageWithSignature {
  private final EncryptedMessage encryptedMessage;
  private final Signature        signature;

  @JsonCreator
  public EncryptedMessageWithSignature(@JsonProperty("encryptedMessage") EncryptedMessage encryptedMessage,
                                       @JsonProperty("signature") Signature signature) {
    this.encryptedMessage = encryptedMessage;
    this.signature = signature;
  }

  public EncryptedMessage getEncryptedMessage() {
    return encryptedMessage;
  }

  public Signature getSignature() {
    return signature;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EncryptedMessageWithSignature that = (EncryptedMessageWithSignature) o;
    return encryptedMessage.equals(that.encryptedMessage) &&
           signature.equals(that.signature);
  }

  @Override
  public int hashCode() {
    return Objects.hash(encryptedMessage, signature);
  }

  @Override
  public String toString() {
    return String.format("EncryptedMessageWithSignature{encryptedMessage=%s, c2=%s}", encryptedMessage, signature);
  }
}
