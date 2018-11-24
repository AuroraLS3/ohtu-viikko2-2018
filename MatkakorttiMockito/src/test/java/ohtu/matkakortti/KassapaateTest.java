
package ohtu.matkakortti;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class KassapaateTest {
    
    Kassapaate kassa;
    Matkakortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = mock(Matkakortti.class);
    }
    
    @Test
    public void kortiltaVelotetaanHintaJosRahaaOn() {
        when(kortti.getSaldo()).thenReturn(10);
        kassa.ostaLounas(kortti);
        
        verify(kortti, times(1)).getSaldo();
        verify(kortti).osta(eq(Kassapaate.HINTA));
    }

    @Test
    public void kortiltaEiVelotetaJosRahaEiRiita() {
        when(kortti.getSaldo()).thenReturn(4);
        kassa.ostaLounas(kortti);
        
        verify(kortti, times(1)).getSaldo();
        verify(kortti, times(0)).osta(anyInt());
    }

    @Test
    public void kortilleLadataanPositiivinenSumma() {
        kassa.lataa(kortti, 5);

        verify(kortti).lataa(eq(5));
    }

    @Test
    public void kortilleEiLadataNegatiivistaSummaa() {
        kassa.lataa(kortti, -5);

        verifyNoMoreInteractions(kortti);
    }
      
}
