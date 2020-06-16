package org.isamm.Agile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Image {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        //image bytes can have large lengths so we specify a value
        //which is more than the default length for picByte column
        @Column(name = "picByte", length = 1000)
        private byte[] picByte;
    public Image( byte[] picByte) {

        this.picByte = picByte;
    }

}
